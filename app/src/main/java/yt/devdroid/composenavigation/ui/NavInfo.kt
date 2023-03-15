package yt.devdroid.composenavigation.ui


fun getRouteWithArgs(route: String, args: Map<String, Any?>): String {
    var argRoute = route.substringBefore("?")
    var hasOptionals = false
    args.map { arg ->
        if (!argRoute.contains(arg.key) && arg.value != null) {
            argRoute += (if (hasOptionals) "&" else "?") + "${arg.key}=${arg.value}"
            hasOptionals = true
        } else {
            argRoute = argRoute.replace("{${arg.key}}", arg.value.toString())
        }
    }
    return argRoute
}
