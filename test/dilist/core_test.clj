(ns dilist.core-test
  (:use clojure.test
        dilist.core))

(def only_deeptech_html (slurp "test/resources/only_deeptech.html"))

(deftest setupValidationTestDataIsNotEmpty
    (is (< 0 (count only_deeptech_html))))

(deftest findSingleChannelDeeptech
  (testing "Not all channels from the file could be found."
    (is (= 1 (count (channels only_deeptech_html))))))

(deftest returnsSingleChannelDeeptechsName
  (testing "Not all channels from the file could be found."
    (is (= "Deep Tech" (:name (first (channels only_deeptech_html)))))))
