(ns art-work.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [art-work.art :as art]
            [clojure.pprint :as pp]
            [clojure.math.numeric-tower :as math]
            [hiccup.core :as h]
            [compojure.route :as route]))

#_(defn make-table [table]
  (map (fn [x]
         [:tr (map (fn [y]
                     [:td (get-num)]) x)]) table))
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



