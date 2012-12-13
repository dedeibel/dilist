(ns dilist.playlist-integration-test
  (:use clojure.test dilist.core))

(def index_html (slurp "test/resources/index.html"))

; TODO Needs to be redefined to check for all entries of a channel
(deftest listWithAllChannels
  (testing "Not all channels from the file could be found."
    (is (= 51 (count (channels index_html))))))

