(ns clojure.lab2
  (:require [clojure.core.async :refer [>! <! <!! chan go go-loop close!]]))

(defn vec-to-chan [vec]
  (let [c (chan) v (conj vec "end")]
    (go (doseq [x v]
          (>! c x))
        (close! c))
    c))

(defn chan-to-vec [c]
  (<!!(go-loop [v [] t [] count 0 i 0]
    (if-let [x (<! c)]
      (if (= i 0)
        (recur v [] x (inc i))
        (do
          (if (= count 0)
            (recur (conj v t) [] x (inc i))
            (recur v (conj t x) (dec count) (inc i)))))
      v))))

(def ch (vec-to-chan [ 3 4 0 2 1 2 2 4 5]))
(println (chan-to-vec ch))
