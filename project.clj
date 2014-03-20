(defproject backup-scheduler "0.1.0-1"
  :description "Schdule builder for backup program"
  :url "https://github.com/rickhall2000/backup-scheduler"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                 [ring-server "0.3.0"]
                 [liberator "0.10.0"]
                 [cheshire "5.2.0"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler backup-scheduler.handler/app
         :init backup-scheduler.handler/init
         :destroy backup-scheduler.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
