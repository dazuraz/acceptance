#!/bin/bash
# requires java, javac.
# Copyright (C) 2012    Lasath Fernando         (@lasath.fernando)
# Copyright (C) 2012    Benjamin James Wright   (@ben.wright)
# Copyright (C) 2012    Damon Stacey            (@damon.stacey)

. compile

if [ `whoami` == "damon" ]; then

   java -ea framework.TestRunner > acceptanceTestResults.txt
   less acceptanceTestResults.txt
   reset

else

   java -ea framework.TestRunner

fi

