(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            )
  (:gen-class))


(def mapa (tsp-core/gerar-cidades))

(println mapa)
;(println (tsp-core/distancia mapa 1 2))
;(println (tsp-core/distancia mapa 2 3))
;(println (tsp-core/distancia mapa 5 4))
;
;;(println (combo/permutations (range 1 6)))
;
;(println (tsp-core/calcula-rota mapa [1 2 3 4 5]))
(println (brute-force/rotas-validas (brute-force/calcular-rotas mapa brute-force/gerar-rotas)))
(println (brute-force/melhor-rota mapa brute-force/gerar-rotas))

(defn -main
  [& args]
  (tsp-core/gerar-cidades)
)
