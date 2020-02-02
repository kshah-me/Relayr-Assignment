*** Settings ***
Documentation    Suite description
Library         RequestsLibrary
Library         Collections

*** Variables ***
${base_url}     http://placegoat.com
${width/height}  /500/500

*** Test Cases ***
Get_WeatherInfo
    create session  myssion     ${base_url}
    ${response}=    get request  myssion        ${width/height}

    #Validations

    ${status_code}=  convert to string   ${response.status_code}
    should be equal   ${status_code}         200

    ${content_bype}=   get from dictionary  ${response.headers}     content-type
    should be equal     ${content_bype}     image/jpeg