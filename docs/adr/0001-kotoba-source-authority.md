# ADR 0001: Kotoba is the Sonpo catalog source authority

- Status: Accepted
- Date: 2026-07-21

`src/association_facts.kotoba` is the sole production source. It preserves the
complete 1991-10-17 establishment and 2024-03-21 revision dates, the second
absent establishment date, the month-only 2026-01 revision date, Japanese UTF-8
titles, official PDF citations, and the asymmetric ordered topic sets. Unknown
associations, aliases, fields, topics, and indexes fail closed; no effects are
declared.

Conformance is observable semantics across the reference evaluator, restricted
JavaScript, and instantiated typed WebAssembly, including the typed ABI, bounds,
effects, and rejection behavior. Compiler-output byte identity is not a language
gate. Clojure and the JVM are compiler/test hosts only.
