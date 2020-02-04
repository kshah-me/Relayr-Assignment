# Library
import requests
import json
import jsonpath

#   API URL
BASE_URI = "https://reqres.in"

#   Read input json file
file = open('C:/Users/Jesna/PycharmProjects/RestApiAssignment/InputData/Post_Req.json','r')
json_input =   file.read()
request_json = json.loads(json_input)

#   Send Request
response = requests.post(BASE_URI + "/api/users",request_json)

# Display response
print(response.status_code)
print(response.content)
print(response.headers)

#   Validation
json_response = json.loads(response.text)
status_code =  response.status_code
job_in_res =  jsonpath.jsonpath(json_response, 'job')
assert job_in_res[0] == "leader"
assert  status_code ==  201

