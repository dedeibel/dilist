(ns dilist.parser-test
  (:use clojure.test
        dilist.parser)
  (:import java.io.ByteArrayInputStream))

(def only-deeptech-html (slurp "test/resources/only_deeptech.html"))

(deftest setup-validation-test-data-is-not-empty
  (is (< 0 (count only-deeptech-html))))

(deftest find-single-channel-deeptech
  (testing "All channels from the file can be found."
           (is (= 1 (count (channels only-deeptech-html))))))

(deftest fdnd-single-channel-deeptech-from-stream
  (testing "Parsing the channels must also be possible from a stream"
           (is (= "Deep Tech" (:name (first (channels (ByteArrayInputStream. (.getBytes only-deeptech-html "UTF-8")))))))))

(deftest returns-single-channel-deeptechs-name
  (testing "Not all channels from the file could be found."
           (is (= "Deep Tech" (:name (first (channels only-deeptech-html)))))))

(deftest first-channel-meta-content-is-correct
  (let [channel (first (channels only-deeptech-html))]
    (is (= "Deep Tech" (:name channel)))))

(deftest steam-url-correct
  (let [channel (first (channels only-deeptech-html))
        streams      (:streams channel)]
    (is (= "http://listen.di.fm/public3/deeptech.pls" (:url (first streams))))))

(deftest other-than-the-first-steam-url-correct
  (let [channel (first (channels only-deeptech-html))
        streams      (:streams channel)]
    (is (= "http://listen.di.fm/public2/deeptech.pls" (:url (nth streams 2))))))

(deftest stream-name-correct
  (let [channel (first (channels only-deeptech-html))
        streams      (:streams channel)]
    (is (= "96k Broadband" (:name (first streams))))))

(deftest other-than-the-first-stream-name-correct
  (let [channel (first (channels only-deeptech-html))
        streams      (:streams channel)]
    (is (= "40k Dialup" (:name (nth streams 2))))))

(deftest stream-format-correct
  (let [channel (first (channels only-deeptech-html))
        streams      (:streams channel)]
    (is (= "MP3" (:format (nth streams 0))))))

