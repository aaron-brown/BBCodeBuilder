#!/bin/bash

#######################################
#                                     #
# Compiles the BBCodeBuilder Project. #
#                                     #
#######################################

echo "#####################"
echo "# Compiling Project #"
echo "#####################"
echo ""

groovyc=`which groovyc`

if [[ -z $? ]]
then
    echo "Groovy / Groovy Compiler not installed."
fi

echo "Using groovyc command: ${groovyc}"
echo ""

src_path=`pwd`"/src/"

echo "Source Path: ${src_path}"
echo ""

bin_path=`pwd`"/bin/"

echo "Local bin path: ${bin_path}"
echo ""

if [[ -e ${bin_path} ]]
then
    echo "You will be prompted to remove the local bin. Please choose Y"
    echo ""
    rm -Ir ${bin_path}
fi

files=`find ${src_path} -name \*.groovy -print`

echo "Files:"
echo "------"
echo "${files}"
echo ""

echo "Compiling..."
echo "------------"
echo "${groovyc} -d ${bin_path} ${files}"

${groovyc} -d ${bin_path} ${files}
echo ""

echo "#####################"
echo "# Project  Compiled #"
echo "#####################"
