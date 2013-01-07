(ns dilist.parser-test
  (:use clojure.test
        dilist.parser)
  (:import java.io.StringReader)
  (:import java.io.ByteArrayInputStream))

(def only_deeptech_html (slurp "test/resources/only_deeptech.html"))

(deftest setupValidationTestDataIsNotEmpty
  (is (< 0 (count only_deeptech_html))))

(deftest findSingleChannelDeeptech
  (testing "All channels from the file can be found."
           (is (= 1 (count (channels only_deeptech_html))))))

(deftest findSingleChannelDeeptechFromStream
  (testing "Parsing the channels must also be possible from a stream"
           (is (= "Deep Tech" (:name (first (channels (ByteArrayInputStream. (.getBytes only_deeptech_html "UTF-8")))))))))

(deftest returnsSingleChannelDeeptechsName
  (testing "Not all channels from the file could be found."
           (is (= "Deep Tech" (:name (first (channels only_deeptech_html)))))))

(deftest firstChannelMetaContentIsCorrect
  (let [channel (first (channels only_deeptech_html))]
    (is (= "Deep Tech" (:name channel)))))

(deftest steamURLCorrect
  (let [channel (first (channels only_deeptech_html))
        streams      (:streams channel)]
    (is (= "http://listen.di.fm/public3/deeptech.pls" (:url (first streams))))))

(deftest otherThanTheFirstSteamURLCorrect
  (let [channel (first (channels only_deeptech_html))
        streams      (:streams channel)]
    (is (= "http://listen.di.fm/public2/deeptech.pls" (:url (nth streams 2))))))

(deftest streamNameCorrect
  (let [channel (first (channels only_deeptech_html))
        streams      (:streams channel)]
    (is (= "96k Broadband" (:name (first streams))))))

(deftest otherThanTheFirstStreamNameCorrect
  (let [channel (first (channels only_deeptech_html))
        streams      (:streams channel)]
    (is (= "40k Dialup" (:name (nth streams 2))))))

(deftest streamFormatCorrect
  (let [channel (first (channels only_deeptech_html))
        streams      (:streams channel)]
    (is (= "MP3" (:format (nth streams 0))))))

