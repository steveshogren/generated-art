(ns art-work.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [art-work.art :as art]
            [clojure.pprint :as pp]
            [hiccup.core :as h]
            [compojure.route :as route]))

(defn make-table [table]
  (map (fn [x y val]
         [:tr [:td (str "x: " x " y: " y " val: " val)]])
       table))

(defn pr [table]
  (h/html [:head
           [:title "A Large Set"]]
          [:body
           [:div
            [:table (make-table table)]]]))

(defroutes app-routes
  (GET "/" [] (str "Test: " (pr (art/generate 50))) )
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))



