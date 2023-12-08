(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            [ifes.tsp.greedy :as greedy]
            [jsonista.core :as json]
            )
  (:gen-class))


(tsp-core/definir-num-cidades 5)


;(def mapa (tsp-core/gerar-cidades))
(def mapa (tsp-core/gerar-cidades-seguro))
;(def mapa (json/read-value (slurp "resources/ex01.json")))
(println mapa)
(println "Mapa inválido: " (tsp-core/mapa-invalido mapa))

(def start-time (System/currentTimeMillis))
(println (brute-force/melhor-rota mapa (brute-force/gerar-rotas)))
(def end-time (System/currentTimeMillis))
(println "Execution time brute force:" (- end-time start-time) "ms")


; Greedy
(def start-time (System/currentTimeMillis))
;(println greedy/primeira-rota)
(println (greedy/melhor-rota mapa))
(def end-time (System/currentTimeMillis))
(println "Execution time greedy:" (- end-time start-time) "ms")

(defn -main
  [& args]
  (tsp-core/gerar-cidades)
)
