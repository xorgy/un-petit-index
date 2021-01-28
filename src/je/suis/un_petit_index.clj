(ns je.suis.un-petit-index
  (:require [clojure.set :refer [intersection]]))

(defn- normalize-nfd [^String s]
  (java.text.Normalizer/normalize s java.text.Normalizer$Form/NFD))

(defn- two-gram [cs]
  (persistent!
   (loop [acc (transient [])
          [head & cs] cs]
     (if cs
       (recur (conj! acc (str head (first cs))) cs)
       acc))))

(defn- mkgrams
  ([s] (mkgrams s 6))
  ([s n]
  (let [cs (vec (.toLowerCase (normalize-nfd (str s))))]
    (reduce
     into #{}
     (reduce
      (fn [acc _] (cons (two-gram (first acc)) acc))
      (list cs)
      (range 0 n))))))

(defn normalized-compare
  ([^String a ^String b] (normalized-compare a b 6))
  ([^String a ^String b n]
   (let [sa (mkgrams a n)
         sca (count sa)
         sci (count (intersection sa (mkgrams b n)))]
     (/ sci sca))))

(defn gramdb-to-index [db]
  (reduce
   (fn [acc [k grams]]
     (reduce #(update %1 %2 (fnil conj #{}) k) acc grams)) {} db))

(defn map-to-gramdb
  ([m] (map-to-gramdb m 6))
  ([m n]
  (into {} (map (fn [[k v]] [k (mkgrams v n)]) m))))

(defn normalized-gramdb-query
  ([g q] (normalized-gramdb-query g q 6))
  ([g q n]
  (let [qg (mkgrams q n)
        qgc (count qg)]
    (->> g
         (pmap (fn [[k v]] [k (count (intersection qg v))]))
         (filter (fn [[k v]] (not (zero? v))))
         (pmap (fn [[k v]] [k (/ v qgc)]))
         (into {})))))

(defn normalized-index-query
  ([i q] (normalized-index-query i q 6))
  ([i q n]
  (let [qg (mkgrams q n)
        qgc (count qg)]
    (->> (select-keys i qg)
         (vals)
         (apply concat)
         (frequencies)
         (pmap (fn [[k v]] [k (/ v qgc)]))
         (into {})))))
