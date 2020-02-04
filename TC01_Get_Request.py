# Library
import requests
import json
import jsonpath

#   API URL
BASE_URI = "http://restapi.demoqa.com"
#   Send Request
response = requests.get(BASE_URI + "/utilities/weather/city/Delhi")

# Display response
print(response.status_code)
print(response.content)
print(response.headers)

#   Validation
json_response = json.loads(response.text)
city = jsonpath.jsonpath(json_response, 'City')
status_code =   response.status_code
assert  city[0] == "Delhi"
assert  status_code ==  200
