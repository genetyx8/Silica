package org.sandboxpowered.silica.client.main

import joptsimple.OptionParser
import joptsimple.OptionSpec
import org.sandboxpowered.silica.api.util.getLogger
import org.sandboxpowered.silica.client.SilicaClient
import org.sandboxpowered.silica.client.SilicaClient.Args
import org.sandboxpowered.silica.util.extensions.ofType

object Main {
    private val logger = getLogger()

    @JvmStatic
    fun main(args: Array<String>) {
        val optionSpec = OptionParser()
        optionSpec.allowsUnrecognizedOptions()
//        Guice.createInjector(SilicaImplementationModule())
        val widthSpec = optionSpec.accepts("width")
            .withRequiredArg()
            .ofType(Int::class)
            .defaultsTo(1000)
        val heightSpec = optionSpec.accepts("height")
            .withRequiredArg()
            .ofType(Int::class)
            .defaultsTo(563)
        val rendererSpec = optionSpec.accepts("renderer")
            .withRequiredArg()
            .ofType(String::class)
            .defaultsTo("")
        val unknownOptionsSpec: OptionSpec<String> = optionSpec.nonOptions()
        val options = optionSpec.parse(*args)
        val unknownOptions = options.valuesOf(unknownOptionsSpec)
        if (unknownOptions.isNotEmpty()) {
            logger.warn("Ignoring arguments: {}", unknownOptions)
        }
        try {
            SilicaClient(
                Args(
                    options.valueOf(widthSpec),
                    options.valueOf(heightSpec),
                    options.valueOf(rendererSpec),
                )
            ).run()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
