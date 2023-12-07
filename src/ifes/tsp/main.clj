(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            [ifes.tsp.greedy :as greedy]
            )
  (:gen-class))


;(def mapa (tsp-core/gerar-cidades))
(def mapa (tsp-core/gerar-cidades-seguro))
;(def mapa {  "1->3"   -1,   "4->5"   -1,   "1->4" 48,   "2->4" 35,   "1->5"   -1,   "3->5"   -1,   "2->3"   -1,   "2->5"})
;(def mapa {"1->3" 40, "4->5" 41, "1->4" -1, "2->4" -1, "1->5" -1, "3->5" -1, "2->3" 46, "2->5" -1, "3->4" -1, "1->2" 8})
(println mapa)
(println "Mapa inválido: " (tsp-core/mapa-invalido mapa))
;(println (tsp-core/cidade-valida? mapa 1))
;(println (tsp-core/cidade-valida? mapa 2))
;(println (tsp-core/cidade-valida? mapa 3))
;(println (tsp-core/cidade-valida? mapa 4))
;(println (tsp-core/cidade-valida? mapa 5))

(def start-time (System/currentTimeMillis))

;(println (tsp-core/distancia mapa 1 2))
;(println (tsp-core/distancia mapa 2 3))
;(println (tsp-core/distancia mapa 5 4))
;
;;(println (combo/permutations (range 1 6)))
;
;(println (tsp-core/calcula-rota mapa [1 2 3 4 5]))
;(println (tsp-core/rotas-validas (brute-force/calcular-rotas mapa brute-force/gerar-rotas)))
;(println (brute-force/melhor-rota mapa brute-force/gerar-rotas))


; Greedy
;(println greedy/primeira-rota)
(println (greedy/melhor-rota mapa greedy/primeira-rota greedy/cidades))

(def end-time (System/currentTimeMillis))
(println (- end-time start-time))

(defn -main
  [& args]
  (tsp-core/gerar-cidades)
)
