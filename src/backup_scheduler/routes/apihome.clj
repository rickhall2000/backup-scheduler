(ns backup-scheduler.routes.apihome
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]))

(defresource api-home
  :handle-ok "hello home"
  :etag "fixed-etag"
  :allowed-methods [:get]
  :available-media-types ["text/plain"])

(defresource get-folders
  :handle-ok "hello data"
  :allowed-methods [:get]
  :etag "fixed-etag"
  :available-media-types ["text/plain"])

(defresource get-folder
  :handle-ok "single folder"
  :allowed-methods [:get]
  :etag "fixed-etag"
  :available-media-types ["Text/plain"])

(defresource create-folder
  :post! ""
  :allowed-methods [:post]
  :etag "fixed-etag"
  :available-media-types ["Text/plain"])

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
