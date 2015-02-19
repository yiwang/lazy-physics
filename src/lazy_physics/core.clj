(ns lazy-physics.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(def times (iterate #(+ % (rand-int 1000)) 0))

(take 30 times)


(take 10 (partition 8 times))

(->> times
     (partition 8)
     (take 10))

(->> times
     (partition 8 1)
     (take 10))

(->> times
     (partition 8 1)
     (map (juxt last first))
     (take 10))

(->> times
     (partition 8 1)
     (map (comp (partial apply -) (juxt last first)))
     (take 10))

((partial + 2) 3)

((comp (partial apply -) (juxt last first)) [3 10])

(->> times
     (partition 8 1)
     (map (comp (partial apply -) (juxt last first)))
     (filter (partial > 1000))
     (take 10))

(->> times
     (partition 8 1)
     (map (juxt identity (comp (partial apply -) (juxt last first))))
     (take 10))

(->> times
     (partition 8 1)
     (map (juxt identity (comp (partial apply -) (juxt last first))))
     (filter (comp (partial > 1000) second))
     (take 10))

(defn smt-8 [times]
  (->> times
       (partition 8 1)
       (map (juxt identity (comp (partial apply -) (juxt last first))))
       (filter (comp (partial > 1000) second))))

(take 10 (smt-8 times))
