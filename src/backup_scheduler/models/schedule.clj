(ns backup-scheduler.models.schedule)

(def data (atom
           {:next-id 3
            :records {1
                      {
                       :folder "c:\\vp10"
                       :full ["Friday"]
                       :incremental ["All"]
                       :target "vp10"}
                      2
                      {
                       :folder "c:\\js"
                       :full ["Friday"]
                       :target "js"}}}))


(defn add-folder [folder]
  (let [id (:next-id @data)
        increment-counter (fn [data]
                            (assoc data :next-id (inc id)))
        updatefn (fn [data]
                   (assoc-in
                    (increment-counter data)
                    [:records id] folder))]
    (swap! data updatefn)
    {id ((@data :records) id)}))

(defn get-folders []
  (:records @data))

(defn get-folder [id]
  {id ((:records @data) id)})


(defn delete-folder [id]
  (let [updatefn (fn [data]
                   (assoc data :records
                          (dissoc (:records data) id)))]
    (swap! data updatefn))
  nil)

(defn update-folder [folder]
  (let [record (first (keys folder))
        updatefn (fn [data]
                   (assoc-in data [:records record]
                             (folder record)))]
    (swap! data updatefn)
    {record ((:records @data) record)}))
