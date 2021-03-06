(ns omniplaylist.difm.parser-test
  (:use clojure.test
        midje.sweet
        omniplaylist.difm.parser)
  (:import java.io.ByteArrayInputStream))

(def only-deeptech-html (slurp "test/resources/difm/only_deeptech.html"))

(deftest setup-validation-test-data-is-not-empty
  (is (< 0 (count only-deeptech-html))))

(deftest find-single-channel-deeptech
  (testing "All channels from the file can be found."
           (is (= 1 (count (parse-all-channels-playlists only-deeptech-html))))))

(deftest fdnd-single-channel-deeptech-from-stream
  (testing "Parsing the channels must also be possible from a stream"
           (is (= "Deep Tech" (:name (first (parse-all-channels-playlists (ByteArrayInputStream. (.getBytes only-deeptech-html "UTF-8")))))))))

(deftest returns-single-channel-deeptechs-name
  (testing "Not all channels from the file could be found."
           (is (= "Deep Tech" (:name (first (parse-all-channels-playlists only-deeptech-html)))))))

(deftest first-channel-meta-content-is-correct
  (let [channel (first (parse-all-channels-playlists only-deeptech-html))]
    (is (= "Deep Tech" (:name channel)))))

(deftest steam-url-correct
  (let [channel (first (parse-all-channels-playlists only-deeptech-html))
        streams      (:streams channel)]
    (is (= "http://listen.di.fm/public2/deeptech.pls" (:url (first streams))))))

(deftest stream-name-correct
  (let [channel (first (parse-all-channels-playlists only-deeptech-html))
        streams      (:streams channel)]
    (is (= "Deep Tech" (:name (first streams))))))

; Detecting the format does not make so much sense with the new di.fm UI
(deftest stream-format-correct
  (let [channel (first (parse-all-channels-playlists only-deeptech-html))
        streams      (:streams channel)]
    (is (nil? (:format (nth streams 0))))))

