(ns art-work.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [art-work.art :as art]
            [clojure.pprint :as pp]
            [compojure.route :as route]))

(defn print-table [aseq column-width]
      (binding [*out* (pp/get-pretty-writer *out*)]
        (doseq [row aseq]
          (doseq [col row]
            (pp/cl-format true "~4D~7,vT" col column-width))
          (prn))))
(defn pr [table]
  (reduce (fn [x y] (str (reduce str x) (reduce str y))) table))

(defroutes app-routes
  (GET "/" [] (str "Test: " (pr (art/generate 10))) )
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))



