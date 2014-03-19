(ns backup-scheduler.routes.home
  (:require [compojure.core :refer :all]
            [backup-scheduler.views.layout :as layout]
            [liberator.core :refer [defresource resource request-method-in]]))

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))
