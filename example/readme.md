In order to run these files, you need to build the project with the following
instruction. And then execute them using the interactive shell runner `grun.sh`

    ./gradlew build copyDevToLib
    .. copy these files into groovysh folder ..
    cd groovysh
    ./grun.sh ../example/filename

You will also need the static binary of wkhtmltox.
