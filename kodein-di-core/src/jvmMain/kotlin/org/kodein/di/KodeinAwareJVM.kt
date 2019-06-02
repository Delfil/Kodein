@file:Suppress("FunctionName")

package org.kodein.di

/**
 * Gets all factories that match the the given argument type, return type and tag.
 *
 * @param A The type of argument the factories take.
 * @param T The type of object to retrieve with the factories.
 * @param argType The type of argument the factories take.
 * @param type The type of object to retrieve with the factories.
 * @param tag The bound tag, if any.
 * @return A list of factories of [T].
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
fun <A, T : Any> KodeinAware.AllFactories(argType: TypeToken<in A>, type: TypeToken<out T>, tag: Any? = null): KodeinProperty<List<(A) -> T>> =
        KodeinProperty(kodeinTrigger, kodeinContext) { ctx, _ -> kodein.container.allFactories(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value) }

/**
 * Gets all providers that match the the given return type and tag.
 *
 * @param T The type of object to retrieve with the providers.
 * @param type The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @return A list of providers of [T].
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
fun <T : Any> KodeinAware.AllProviders(type: TypeToken<out T>, tag: Any? = null): KodeinProperty<List<() -> T>> =
        KodeinProperty(kodeinTrigger, kodeinContext) { ctx, _ -> kodein.container.allProviders(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value) }

/**
 * Gets all providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param argType The type of argument the curried factories take.
 * @param type The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg A function that returns the argument that will be given to the factory when curried.
 * @return A list of providers of [T].
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
fun <A, T : Any> KodeinAware.AllProviders(argType: TypeToken<in A>, type: TypeToken<out T>, tag: Any? = null, arg: () -> A): KodeinProperty<List<() -> T>> =
        KodeinProperty(kodeinTrigger, kodeinContext) { ctx, _ -> kodein.container.allFactories(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value).map { it.toProvider(arg) } }

/**
 * Gets all instances from providers that match the the given return type and tag.
 *
 * @param T The type of object to retrieve with the providers.
 * @param type The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @return A list of [T] instances.
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
fun <T : Any> KodeinAware.AllInstances(type: TypeToken<out T>, tag: Any? = null): KodeinProperty<List<T>> =
        KodeinProperty(kodeinTrigger, kodeinContext) { ctx, _ -> kodein.container.allProviders(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value).map { it.invoke() } }

/**
 * Gets all instances from providers that match the the given return type and tag, curried from factories that take an argument [A].
 *
 * @param A The type of argument the curried factories take.
 * @param T The type of object to retrieve with the providers.
 * @param argType The type of argument the curried factories take.
 * @param type The type of object to retrieve with the providers.
 * @param tag The bound tag, if any.
 * @param arg A function that returns the argument that will be given to the factory when curried.
 * @return A list of [T] instances.
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
fun <A, T : Any> KodeinAware.AllInstances(argType: TypeToken<in A>, type: TypeToken<T>, tag: Any? = null, arg: () -> A): KodeinProperty<List<T>> =
        KodeinProperty(kodeinTrigger, kodeinContext) { ctx, _ -> kodein.container.allFactories(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value).map { it.invoke(arg()) } }
