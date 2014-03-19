(ns backup-scheduler.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [backup-scheduler.routes.home :refer [home-routes]]
            [backup-scheduler.routes.apihome :refer [api-routes]]))

(defn init []
  (println "backup-scheduler is starting"))

(defn destroy []
  (println "backup-scheduler is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes api-routes  app-routes)
      (handler/site)
      (wrap-base-url)))
