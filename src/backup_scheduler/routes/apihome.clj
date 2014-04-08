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
  :post! (fn [ctx]
           (let [body (slurp (get-in ctx [:request :body]))]
               (m/add-folder body)))
  :available-media-types ["application/edn"])

(defresource folder [id]
  :allowed-methods [:get :put :delete]
  :handle-ok  (fn [_] (m/get-folder (read-string id)))
  :delete! (fn [_] (m/delete-folder (read-string id)))
  :put! (fn [ctx]
          (let [body (read-string (slurp (get-in ctx [:request :body])))]
            (m/update-folder (read-string id) body)))
  :etag "fixed-etag"
  :available-media-types ["application/edn"])

(defroutes api-routes
  (GET "/api" request api-home)
  (ANY "/api/folders" request folders)
  (ANY "/api/folders/:id" [id] (folder id)))
