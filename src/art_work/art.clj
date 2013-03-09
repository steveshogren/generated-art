(ns art-work.art
  (:use [clojure.math.numeric-tower :only (round)]))

(declare get-num generate color-cell?
         neigbors populate-random grow
         not-nil?  update-cell
         update-around-cell looping)
(defn rand-num []
  (round (rand 6)))

(defn get-num []
  (if (color-cell?) (rand-num) nil))

(defn not-nil? [x] (not (nil? x)))

(defn only-set [board]
  (filter (fn [[x y val]] (not-nil? val)) board))

(defn looping [fun cells-to-loop board]
  (loop [new-board board
         cells cells-to-loop]
    (cond
     (empty? cells) new-board
     :else (recur (fun (first cells) new-board)
                  (rest cells)))))

(defn grow [board]
  (looping (fn [cell board]
             (update-around-cell cell 1 board))
           (only-set board)
           board))

(defn update-around-cell [[x y _] newval board]
  (looping (fn [cell board]
             (update-cell cell (rand-num) board))
           (if (> (rand 1) 0.5) (neigbors [x y] :true) (neigbors [x y]))
           board))

(defn update-cell [[x y] newval board]
  (map (fn [[x1 y1 val]]
         (cond
          (and (= x1 x) (= y1 y)) [x1 y1 newval]
          :else [x1 y1 val]))
       board))

(defn generate [size]
  (grow (populate-random size)))

(defn populate-random [size]
  (for [x (range size) y (range size)]
    (let [val (get-num)]
      (cond
       (nil? val) [x y nil]
       :else [x y val]))))

(defn color-cell? []
  (> (rand 1) 0.99))

(defn neigbors
  ([[x y] larger] (for [dx [-2 -1 0 1 2] dy
                          (if (zero? dx) [-2 -1 1 2] [-2 -1 0 1 2])]
                      [(+ dx x) (+ dy y)]))
  ([[x y]] (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
              [(+ dx x) (+ dy y)])))


