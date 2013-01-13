(ns dilist.stream_test
  [:use dilist.stream]
  [:use clojure.test])

(deftest premium-stream-is-recognized
  (testing "A premium stream is recognized"
           (is (= true (is-premium (map->Stream {:format "MP3" :name "128k Broadband (Premium)" :url "/premium"})))))
  (testing "A normal stream is not recognized as premium"
           (is (= false (is-premium (map->Stream {:format "MP3" :name "128k Broadband" :url "http://listen.di.fm/public2/chiptunes.pls"}))))))


