(ns backup-scheduler.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to backup-scheduler"]
     (include-css "/css/screen.css")]
    [:body body]))
