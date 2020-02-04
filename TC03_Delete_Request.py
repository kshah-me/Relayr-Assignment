# Library
import requests

#   API URL
BASE_URI =   "https://reqres.in"

#   Send Request
response = requests.delete(BASE_URI + "/api/users/2")

# Display response
print(response.status_code)
print(response.headers)

#   Validation
status_code =   response.status_code
assert  status_code ==  204
