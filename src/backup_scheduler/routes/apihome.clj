(ns backup-scheduler.routes.apihome
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [backup-scheduler.models.schedule :as m]))

(defresource api-home
  :handle-ok "hello home"
  :allowed-methods [:get]
  :available-media-types ["text/plain"])

(defresource folders
  :allowed-methods [:get :post]
  :handle-ok (m/get-folders)
  :post! (fn [context]
           (let [body (slurp (get-in context [:request :body]))]
               (m/add-folder body)))
  :available-media-types ["application/edn"])

(defresource folder [id]
  :allowed-methods [:get :put :delete]
  :handle-ok  (m/get-folder (read-string id))
  :etag "fixed-etag"
  :available-media-types ["application/edn"])

(defroutes api-routes
  (GET "/api" request api-home)
  (ANY "/api/folders" request folders)
  (ANY "/api/folders/:id" [id] (folder id)))
