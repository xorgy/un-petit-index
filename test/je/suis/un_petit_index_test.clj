(ns je.suis.un-petit-index-test
  (:require [clojure.test :refer :all]
            [je.suis.un-petit-index :refer :all]))

(def testdb
  {
   :제26조1 "모든 사람은 교육을 받을 권리를 가진다 . 교육은 최소한 초등 및 기초단계에서는 무상이어야 한다. 초등교육은 의무적이어야 한다. 기술 및 직업교육은 일반적으로 접근이 가능하여야 하며, 고등교육은 모든 사람에게 실력에 근거하여 동등하게 접근 가능하여야 한다."
   :제26조2 "교육은 인격의 완전한 발전과 인권과 기본적 자유에 대한 존중의 강화를 목표로 한다. 교육은 모든 국가 , 인종 또는 종교 집단간에 이해, 관용 및 우의를 증진하며 , 평화의 유지를 위한 국제연합의 활동을 촉진하여야 한다."
   :제26조3 "부모는 자녀에게 제공되는 교육의 종류를 선택할 우선권을 가진다 ."
   :제27조1 "모든 사람은 공동체의 문화생활에 자유롭게 참여하며 예술을 향유하고 과학의 발전과 그 혜택을 공유할 권리를 가진다 ."
   :제27조2 "모든 사람은 자신이 창작한 과학적 , 문학적 또는 예술적 산물로부터 발생하는 정신적, 물질적 이익을 보호받을 권리를 가진다 ."
   :foo "this, that, and the other thing thing. A rose by any other name is as sweet."
   :bar "Another one bites the dust. To be, or not to be, that is the question; whether 'tis nobler in the mind to suffer the slings and arrows of outrageous fortune; or to take arms against a sea of troubles and by opposing, end them."
   :baz "To die, to sleep. No more; and by a sleep to say we end the heartache and the thousand natural shocks that flesh is heir to. 'Tis a consummation devoutly to be wished. To die. To sleep."
   :qux "To sleep. Perchance to dream; ay, there's the rub: For in that sleep of death, what dreams may come when we have shuffled off this mortal coil, must give us pause."
   :quux 69420
   :quuux 42069
   :fungus :balthazar
   :virus :bazaar
   :helicopter #{:fungus "trilogy" :balance}
   :wiki-a "design kernels that allow machine learning algorithms such as support vector machines to learn from string data"
   :wiki-b "find likely candidates for the correct spelling of a misspelled word"
   :wiki-c "improve compression in compression algorithms where a small area of data requires n-grams of greater length"
   :wiki-d "assess the probability of a given word sequence appearing in text of a language of interest in pattern recognition systems, speech recognition, OCR (optical character recognition), Intelligent Character Recognition (ICR), machine translation and similar applications"
   :wiki-e "improve retrieval in information retrieval systems when it is hoped to find similar \"documents\" (a term for which the conventional meaning is sometimes stretched, depending on the data set) given a single query document and a database of reference documents"
   :wiki-f "improve retrieval performance in genetic sequence analysis as in the BLAST family of programs"
   :wiki-g "identify the language a text is in or the species a small sequence of DNA was taken from"
   :wiki-h "predict letters or words at random in order to create text, as in the dissociated press algorithm."
   :wiki-i "cryptanalysis"
   })

(def testgramdb (map-to-gramdb testdb))
(def testindex (gramdb-to-index testgramdb))

(deftest index-and-gramdb-round-trip-identical
  (testing "Conversions between the gramdb and index formats should be lossless"
    (is (= testgramdb (gramdb-to-index testindex)))))

(deftest index-and-gramdb-results-identical
  (testing "Queries against the gramdb and index formats should return identical results"
    (is (= (normalized-index-query  testindex  "#{:balance :fungus}")
           (normalized-gramdb-query testgramdb "#{:balance :fungus}")))
    (is (= (normalized-index-query  testindex  420)
           (normalized-gramdb-query testgramdb 420)))
    (is (= (normalized-index-query  testindex  "한다.")
           (normalized-gramdb-query testgramdb "한다.")))
    ))