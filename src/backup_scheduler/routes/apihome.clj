(ns backup-scheduler.routes.apihome
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [backup-scheduler.models.schedule :as m]))

(defresource api-home
  :handle-ok "hello home"
  :etag "fixed-etag"
  :allowed-methods [:get]
  :available-media-types ["text/plain"])

(defresource get-folders
  :handle-ok (m/get-folders)
  :allowed-methods [:get]
  :etag "fixed-etag"
  :available-media-types ["application/edn"])

(defresource get-folder
  :handle-ok (fn [context]
               (let [params (get-in context [:request :route-params])]  (m/get-folder (read-string (:id params))) ))
  :allowed-methods [:get]
  :etag "fixed-etag"
  :available-media-types ["application/edn"])

(defresource create-folder
  :post! (fn [context]
           (let [body (slurp (get-in context [:request :body]))]
               (m/add-folder body)))
  :allowed-methods [:post]
  :etag "fixed-etag"
  :available-media-types ["application/edn" "Text/plain"])

(defresource update-folder
  :handle-ok "update folder"
  :allowed-methods [:put]
  :etag "fixed-etag"
  :available-media-types ["Text/plain"])

(defresource delete-folder
  :handle-ok "delete folder"
  :allowed-methods [:delete]
  :etag "fixed-etag"
  :available-media-types ["Text/plain"])

(defroutes api-routes
  (GET "/api" request api-home)
  (GET "/api/folders" request get-folders)
  (GET "/api/folders/:id" request get-folder)
  (POST "/api/folders" request create-folder)
  (PUT "/api/folders/:id" request update-folder)
  (DELETE "/api/folders/:id" request delete-folder))
