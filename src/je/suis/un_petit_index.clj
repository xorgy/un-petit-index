(ns je.suis.un-petit-index
  (:require [clojure.set :refer [intersection union]]))

(defn- normalize-nfd [^String s]
  (java.text.Normalizer/normalize s java.text.Normalizer$Form/NFD))

(defn- mkgrams
  ([s] (mkgrams s 6))
  ([s n]
  (let [cs (vec (.toLowerCase (normalize-nfd (str s))))]
    (into #{}
          (mapcat (fn [n] (map #(apply str %) (partition n 1 cs))) (range 1 (inc n)))))))

(defn normalized-compare
  ([^String a ^String b] (normalized-compare a b 6))
  ([^String a ^String b n]
   (let [sa (mkgrams a n)
         sca (count sa)
         sci (count (intersection sa (mkgrams b n)))]
     (/ sci sca))))

(defn pairs-to-gramdb
  ([pairs] (pairs-to-gramdb pairs 6))
  ([pairs n]
  (pmap (fn [[k v]] [k (mkgrams v n)]) pairs)))

(defn gramdb-assoc
  ([g k v] (gramdb-assoc g k v 6))
  ([g k v n]
   (assoc g k (mkgrams v))))

(defn gramdb-to-index [db]
  (reduce
   (fn [acc [k grams]]
     (reduce #(update %1 %2 (fnil conj #{}) k) acc grams)) {} db))

(defn index-assoc
  ([i k v] (index-assoc i k v 6))
  ([i k v n]
   (reduce #(update %1 %2 (fnil conj #{}) k) i (mkgrams v n))))

(defn index-insert-pairs
  ([i pairs] (index-insert-pairs i pairs 6))
  ([i pairs n]
   (reduce
    (fn [acc [k v]]
      (reduce #(update %1 %2 (fnil conj #{}) k) acc (mkgrams v n))) i pairs)))

(defn merge-indices [ & indices]
  (apply merge-with union indices))

(defn pairs-to-index
  ([pairs] (pairs-to-index pairs 6))
  ([pairs n]
   (index-insert-pairs {} pairs n)))

(defn gramdb-query
  ([g q] (gramdb-query g q 6))
  ([g q n]
  (let [qg (mkgrams q n)]
    (->> g
         (pmap (fn [[k v]]
                 (let [ni (count (intersection qg v))]
                   (when-not (zero? ni) [k ni]))))
         (filter some?)))))

(defn normalized-gramdb-query
  ([g q] (normalized-gramdb-query g q 6))
  ([g q n]
  (let [qg (mkgrams q n)
        qgc (count qg)]
    (->> g
         (pmap (fn [[k v]]
                 (let [ni (count (intersection qg v))]
                   (when-not (zero? ni) [k (/ ni qgc)]))))
         (filter some?)))))

(defn index-query
  ([i q] (index-query i q 6))
  ([i q n]
   (->> (mkgrams q n)
        (mapcat i)
        (frequencies))))

(defn normalized-index-query
  ([i q] (normalized-index-query i q 6))
  ([i q n]
  (let [qg (mkgrams q n)
        qgc (count qg)]
    (->> (mapcat i qg)
         (frequencies)
         (map (fn [[k v]] [k (/ v qgc)]))))))
