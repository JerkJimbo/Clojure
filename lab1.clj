(ns clojure.lab1)

(defn sum [a n] (reduce + (take n a)))
(defn lab [a n sum]
  (when-not (empty? a)
    (when (<= n (count a))
      (lazy-seq(cons (sum a n)  (lab (drop n a) n sum))))))
(lab '(1 2 3 4 5 6 7 8 9 10) 2 sum)
