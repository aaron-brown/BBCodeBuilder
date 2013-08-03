#!/bin/bash

####################################
#                                  #
# Tests the BBCodeBuilder Project. #
#                                  #
####################################

echo "###################"
echo "# Testing Project #"
echo "###################"
echo ""

root_path=`pwd`
bin_path="${root_path}/bin"
if [[ ! -e ${bin_path} ]]
then
    echo "Project not compiled; compiling..."
    "${root_path}/compile.bash"
fi

groovy=`which groovy`

if [[ -z $? ]]
then
    echo "Groovy not installed."
fi

echo "Using groovy command: ${groovy}"
echo ""

test_path="${root_path}/test"

files=`find ${test_path} -name \*.groovy -print`

echo "Files:"
echo "------"
echo "${files}"
echo ""

echo "Testing..."
echo "----------"
echo "${groovy} -cp .:${bin_path} ${files}"
echo ""

groovy="${groovy} -cp .:${bin_path}"
${groovy} ${files}

echo "###################"
echo "# Project  Tested #"
echo "###################"
