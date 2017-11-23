(ns day-calendar.utils)

;; Constants

(def fifteen-min 0.25)

(def eod 17)


;; Utils

(defn time-range
  "Generates a range of times incrementing in .25 (fifteen min) blocks.
  For example, `[9 9.25 9.5 ...]`"
  ([]
   (time-range 9))
  ([start-time]
   (range start-time eod fifteen-min)))


(defn time+hour+min
  "Capture the hours and minutes for the time provided - returns a vector."
  [time]
  (let [match (re-matches #"([0-9]{1,2})([.][0-9]{1,2})" time)]
    (if (nil? match)
      []
      match)))


(defn format-time
  "Format the time provided into a human friendly time format.
  For example, we store the time 9:15 as 9.25. This will convert 9.25 to 9:15."
  [time]
  (let [time-string (str time)
        [original-time hour minutes] (time+hour+min time-string)]
    (cond
      (nil? original-time)
      (str time-string ":00")

      (= ".25" minutes)
      (str hour ":15")

      (= ".5" minutes)
      (str hour ":30")

      :else
      (str hour ":45"))))