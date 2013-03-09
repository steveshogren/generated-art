(ns art-work.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [art-work.art :as art]
            [clojure.pprint :as pp]
            [hiccup.core :as h]
            [hiccup.page :as p]
            [compojure.route :as route]))

(defn make-row [[x y val]]
  (cond
   (nil? val) [:td {:class "empty"} ""]
   :else [:td {:class (str "num" val)}  ""]))

(defn m-table [table]
  (map
   (fn [rows]
     [:tr (map (fn [row] (make-row row)) rows)])
   (partition-by first table)))

(defn pr [table]
  (h/html [:head
           [:title "A Large Set"]]
          (p/include-css "style.css")
          [:body
           [:div
            [:table (m-table table)]]]))

(defroutes app-routes
  (GET "/" [] (str "Test: " (pr (art/generate 50))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))



