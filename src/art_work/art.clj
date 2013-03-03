(ns art-work.art)

(defn generate [size]
  (map (fn [x] (range 1 size)) (range 1 size)))
