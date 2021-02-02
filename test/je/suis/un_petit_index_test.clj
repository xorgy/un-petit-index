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
   :孫子算經 "《序》 序: 孫子曰：夫算者，天地之經緯，群生之元首；五常之本末，陰陽之父母； 星辰之建號，三光之表裹；五行之準平，四時之終始；萬物之祖宗，六藝之綱紀。 稽群倫之聚散，考二氣之降升；推寒暑之迭運，步遠近之殊同；觀天道精微之兆 基，察地理從橫之長短；采神祇之所在，極成敗之符驗；窮道德之理，究性命之 情。立規矩，準方圓，謹法度，約尺丈，立權衡，平重輕，剖毫釐，析黍絫；歷 億載而不朽，施八極而無疆。散之不可勝究，斂之不盈掌握。嚮之者富有餘，背 之者貧且窶；心開者幼沖而即悟，意閉者皓首而難精。夫欲學之者必務量能揆己， 志在所專。如是則焉有不成者哉。"
   :第1卷上 "度之所起，起於忽。欲知其忽，蠶所生，吐絲為忽。十忽為一秒，十秒 為一毫，十毫為一釐，十釐為一分，十分為一寸，十寸為一尺，十尺為一丈，十 丈為一引；五十尺為一端；四十尺為一疋；六尺為一步。二百四十步為一畝。三 百步為一里。"
   :第2卷上  "稱之所起，起於黍。十黍為一絫，十絫為一銖，二十四銖為一兩，十六 兩為一斤，三十斤為一鉤，四鉤為一石。量之所起，起於粟。六粟為一圭，十圭 為一抄，十抄為一撮，十撮為一勺，十勺為一合，十合為一升，十升為一斗，十 斗為一斛。斛得六千萬粟。所以得知者，六粟為一圭，十圭六十粟為一抄，十抄 六百粟為一撮，十撮六千粟為一勺，十勺六萬粟為一合，十合六十萬粟為一升， 十升六百萬粟為一斗，十斗六千萬粟為一斛。十斛六億粟，百斛六兆粟，千斛六 京粟，萬斛六陔粟，十萬斛六秭粟，百萬斛六壤粟，千萬斛六溝粟，萬萬斛為一 億斛六澗粟，十億斛六正粟，百億斛六載粟。"
   :第3卷上  "凡大數之法，萬萬曰億，萬萬億曰兆，萬萬兆曰京，萬萬京曰陔，萬萬 陔曰秭，萬萬秭曰壤，萬萬壤曰溝，萬萬溝曰澗，萬萬澗日正，萬萬正曰載。"
   :第4卷上  "周三徑一。方五邪七；見邪求方，五之，七而一；見方求邪，七之，五 而一。"
   :第5卷上  "黃金方寸重一斤。白金方寸重一十四兩。玉方寸重一十二兩。銅方寸重 七兩半。鈆方寸重九兩半。鐵方寸重六兩。石方寸重三兩。"
   :第6卷上  "凡筭之法，先識其位，一從十橫，百立千僵，千十相望，萬百相當。"
   :第7卷上  "凡乘之法，重置其位。上下相觀，上位有十步至十，有百步至百，有千 步至千。以上命下，所得之數列於中位。言十即過，不滿自如。上位乘訖者先去 之。下位乘訖者則俱退之。六不積，五不隻。土下相乘，至盡則已。"
   :第8卷上  "凡除之法，與乘正異。乘得在中央，除得在上方。假令六為法，百為實。 以六除百，當進之二等，令在正百下，以六除一，則法多而實少，不可除，故當 退就十位。以法除實，言一六而折百為四十七，故可除。若實多法少，自當百之， 不當復退。故或步法十者置於十位，百者置於百位。上位有空絕者，法退二位。 餘法皆如乘時。實有餘者，以法命之，以法為母。實餘為子。"
   :第9卷上  "以粟求糲米，三之，五而一。以糲米求粟，五之，三而一。以糲米求飯， 五之，二而一。以粟米求糲飯，六之，四而一。以糲飯求糲米，二之，五而一。 以绺米求飯，八之，四而一。"
   :第10卷上  "十分減一者，以二乘，二十除。減二者，以四乘，二十除。減三者， 以六乘，二十除。減四者，以八乘，二十除。減五者，以十乘，二十除。減六者， 以十二乘，二十除。減七者，以十四乘，二十除。減八者，以十六乘，二十除。 減九者，以十八乘，二十除。"
   :第11卷上  "九分減一者，以二乘，十八除。"
   :第12卷上  "八分減一者，以二乘，十六除。"
   :第13卷上  "七分減一者，以二乘，十四除。"
   :第14卷上  "六分減一者，以二乘，十二除。"
   :第15卷上  "五分減一者，以二乘，十除。"
   :第16卷上  "九九八十一，自相乘，得幾何？答曰：六千五百六十一。術曰：重置 其位，以上八呼下八，八八六十四，即下六千四百於中位。以上八呼下一，一八 如八，即於中位下八十。退下位一等，收上位八十。以上位一呼下八，一八如八， 即於中位下八十。以上一呼下一，一一如一，即於中位下一。上下位俱收，中位 即得六千五百六十一。"
   :第17卷上  "六千五百六十一，九人分之，問人得幾何？答曰：七百二十九。術曰： 先置六千五百六十一於中位，為實。下列九人為法。上位置七百，以上七呼下九， 七九六十三，即除中位六千三百。退下位一等，即上位置二十。以上二呼下九， 二九十八，即除中位一百八十。又更退下位一等，即上位更置九，即以上九呼下 九，九九八十一，即除中位八十一。中位邬盡，收下位。上位所得即人之所得。 自八八六十四至一一如一，邬準此。"
   :第18卷上  "八九七十二，自相乘，得五千一百八十四。八人分之，人得六百四十 八五。七九六十三，自相乘，得三千九百六十九。七人分之，人得五百六十七五。"
   :第19卷上  "六九五十四，自相乘，得二千九百一十六。六人分之，人得四百八十 六。"
   :第20卷上  "五九四十五，自相乘，得二千二十五。五人分之，人得四百五。"
   :第21卷上  "四九三十六，自相乘，得一千二百九十六。四人分之，人得三百二十 四。"
   :第22卷上  "三九二十七，自相乘，得七百二十九。三人分之，人得二百四十三。"
   :第23卷上  "二九一十八，自相乘，得三百二十四。二人分之，人得一百六十二。"
   :第24卷上  "一九如九，自相乘，得八十一。一人得八十一。右九九一條，得四百 五，自相乘，得一十六萬四千二十五。九人分之，人得二萬八千二百二十五。"
   :第25卷上  "八八六十四，自相乘，得四千九十六。八人分之，人得五百十二。"
   :第26卷上  "七八五十六，自相乘，得三千一百三十六。七人分之，人得四百四十 八。"
   :第27卷上  "六八四十八，自相乘，得二千三百四。六人分之，人得三百八十四。"
   :第28卷上  "五八四十，自相乘，得一千六百。五人分之，人得三百二十。"
   :第29卷上  "四八三十二，自相乘，得一千二十四。四人分之，人得二百五十六。"
   :第30卷上  "三八二十四，自相乘，得五百七十六。三人分之，人得一百九十二。"
   :第31卷上  "二八十六，自相乘，得二百五十六。二人分之，人得一百二十八。"
   :第32卷上  "一八如八，自相乘，得六十四。一人得六十四。右八八一條，得二百 八十八，自相乘，得八萬二千九百四十四。八人分之，人得一萬三百六十八。"
   :第33卷上  "七七四十九，自相乘，得二千四百一。七人分之，人得三百四十三。"
   :第34卷上  "六七四十二，自相乘，得一千七百六十四。六人分之，人得二百九十 四。"
   :第35卷上  "五七三十五，自相乘，得一千二百二十五。五人分之，人得二百四十 五。"
   :第36卷上  "四七二十八，自相乘，得七百八十四。四人分之，人得一百九十六。"
   :第37卷上  "三七二十一，自相乘，得四百四十一。三人分之，人得一百四十七。"
   :第38卷上  "二七十四，自相乘，得一百九十六。二人分之，人得九十八。"
   :第39卷上  "一七如七，自相乘，得四十九。一人得四十九。右七七一條，得一百 九十六，自相乘，得三萬八千四百一十六。七人分之，人得五千四百八十八。"
   :第40卷上  "六六三十六，自相乘，得一千二百九十六。六人分之，人得二百一十 六。"
   :第41卷上  "五六三十，自相乘，得九百。五人分之，人得一百八十。"
   :第42卷上  "四六二十四，自相乘，得五百七十六。四人分之，人得一百四十四。"
   :第43卷上  "三六一十八，自相乘，得三百二十四。三人分之，人得一百八。"
   :第44卷上  "二六一十二，自相乘，得一百四十四。二人分之，人得七十二。"
   :第45卷上  "一六如六，自相乘，得三十六。一人得三十六。右六六一條，得一百 二十六，自相乘，得一萬五千八百七十六。六人分之，人得二千六百四十六。"
   :第46卷上  "五五二十五，自相乘，得六百二十五。五人分之，人得一百二十五。"
   :第47卷上  "四五二十，自相乘，得四百。四人分之，人得一百。"
   :第48卷上  "三五一十五，自相乘，得二百二十五。三人分之，人得七十五。"
   :第49卷上  "二五一十，自相乘，得一百。二人分之，人得五十。"
   :第50卷上  "一五如五，自相乘，得二十五。一人得二十五。右五五一條，得七十 五，自相乘，得五千六百二十五。五人分之，人得一千一百二十五。"
   :第51卷上  "四四一十六，自相乘，得二百五十六。四人分之，人得六十四。"
   :第52卷上  "三四一十二，自相乘，得一百四十四。三人分之，人得四十八。"
   :第53卷上  "二四如八，自相乘，得六十四。二人分之，人得三十二。"
   :第54卷上  "一四如四，自相乘，得一十六。一人得一十六。右四四一條，得四十， 自相乘，得一千六百。四人分之，人得四百。"
   :第55卷上  "三三如九，自相乘，得八十一。三人分之，人得二十七。"
   :第56卷上  "二三如六，自相乘，得三十六。二人分之，人得一十八。"
   :第57卷上  "一三如三，自相乘，得九。一人得九。右三三一條，得一十八，自相 乘，得三百二十四。三人分之，人得一百八。"
   :第58卷上  "二二如四，自相乘，得一十六。二人分之，人得八。"
   :第59卷上  "一二如二，自相乘，得四。一人得四。右二二一條，得六，自相乘， 得三十六。二人分之，人得十八。"
   :第60卷上  "一一如一，自相乘，得一。一乘不長。右從九九至一一，總成一千一 百五十五，自相乘，得一百十三萬四千二十五。九人分之，人得一十四萬八千二 百二十五。"
   :第61卷上  "以九乘一十二，得一百八。六人分之，人得一十八。"
   :第62卷上  "以二十七乘三十六，得九百七十二。一十八人分之，人得五十四。"
   :第63卷上  "以八十一乘一百八，得八千七百四十八。五十四人分之，人得一百六 十二。"
   :第64卷上  "以二百四十三乘三百二十四，得七萬八千七百三十二。一百六十二人 分之，人得四百八十六。"
   :第65卷上  "以七百二十九乘九百七十二，得七十萬八千五百八十八。四百八十六 人分之，人得一千四百五十八。"
   :第66卷上  "以二千一百八十七乘二千九百一十六，得六百三十七萬七千百九十 二。一千四百五十八人分之，人得四千三百七十四。"
   :第67卷上  "以六千五百六十一乘八千七百四十八，得五千七百三十九萬五千六百 二十八。四千三百七十四人分之，人得一萬三千一百二十二。"
   :第68卷上  "以一萬九千六百八十三乘二萬六千二百四十四，得五億一千六百五十 六萬六百五十二。一萬三千一百二十二人分之，人得三萬九千三百六十六。"
   :第69卷上  "以五萬九千四十九乘七萬八千七百三十二，得四十六億四千九百四萬 五千八百六十八。三萬九千三百六十六人分之，人得一十一萬八千九十八。"
   :第70卷上  "以一十七萬七千一百四十七乘二十三萬六千一百九十六，得四百一十 八億四千一百四十一萬二千八百一十二。一十一萬八千九十八人分之，人得三十 五萬四千二百九十四。"
   :第71卷上  "以五十三萬一千四百四十一乘七十萬八千五百八十八，得三千七百六 十五億七千二百七十一萬五千三百八。三十五萬四千二百九十四人分之，人得一 百六萬二千八百八十二。"
   })

(def testgramdb (pairs-to-gramdb testdb))
(def testindex (gramdb-to-index testgramdb))

(deftest index-equivalence-guarantees
  (testing "Conversions between the gramdb and index formats should be lossless"
    (is (= (into {} testgramdb) (gramdb-to-index testindex))))

  (testing "Incremental indexes should be identical to one-shot indexes"
    (is (= (reduce-kv index-assoc {} testdb) testindex))
    (is (= (reduce-kv gramdb-assoc {} testdb) (into {} testgramdb))))

  (testing "Queries against the gramdb and index formats should return identical results"
    (is (= (into {} (normalized-index-query  testindex  "#{:balance :fungus}"))
           (into {} (normalized-gramdb-query testgramdb "#{:balance :fungus}"))))
    (is (= (into {} (normalized-index-query  testindex  420))
           (into {} (normalized-gramdb-query testgramdb 420))))
    (is (= (into {} (normalized-index-query  testindex  "한다."))
           (into {} (normalized-gramdb-query testgramdb "한다."))))
    (is (= (into {} (index-query  testindex  "#{:balance :fungus}"))
           (into {} (gramdb-query testgramdb "#{:balance :fungus}"))))
    (is (= (into {} (index-query  testindex  420))
           (into {} (gramdb-query testgramdb 420))))
    (is (= (into {} (index-query  testindex  "한다."))
           (into {} (gramdb-query testgramdb "한다."))))
    ))
