(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            [ifes.tsp.greedy :as greedy]
            )
  (:gen-class))


;(def mapa (tsp-core/gerar-cidades))
(def mapa (tsp-core/gerar-cidades-seguro))
(println mapa)
(println "Mapa inválido: " (tsp-core/mapa-invalido mapa))

(def start-time (System/currentTimeMillis))

(println (brute-force/melhor-rota mapa brute-force/gerar-rotas))


; Greedy
;(println greedy/primeira-rota)
(println (greedy/melhor-rota mapa greedy/primeira-rota greedy/cidades))

(def end-time (System/currentTimeMillis))
(println (- end-time start-time))

(defn -main
  [& args]
  (tsp-core/gerar-cidades)
)
