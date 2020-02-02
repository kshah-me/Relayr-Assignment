*** Settings ***
Documentation    Suite description
Library         RequestsLibrary
Library         Collections

*** Variables ***
${base_url}     http://restapi.demoqa.com
${city}  Delhi

*** Test Cases ***
Get_WeatherInfo
    create session  myssion     ${base_url}
    ${response}=    get request  myssion    /utilities/weather/city/${city}
    #log to console  ${response.status_code}
    #Validations

    #${status_code}=  convert to string   ${response.status_code}
    #should be equal   ${status_code}         200
    #${body_content}=  convert to string    ${response.content}
    #should contain  ${body_content}     ${city}
    ${content_bype}=   get from dictionary  ${response.headers}     content-type
    should be equal     ${content_bype}     application/json