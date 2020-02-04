# Library
import requests
import json
import jsonpath
import  pytest

#   API URL
BASE_URI = "https://reqres.in"

class TestUser():
    @pytest.fixture()
    def test_create_user_details(self):
        file = open('C:/Users/Jesna/PycharmProjects/RestApiAssignment/InputData/Post_Req.json', 'r')
        json_input = file.read()
        request_json = json.loads(json_input)
        response = requests.post(BASE_URI + "/api/users", request_json)
        print(response.status_code)
        print(response.content)
        print(response.headers)
        yield
        json_response = json.loads(response.text)
        status_code = response.status_code
        job_in_res = jsonpath.jsonpath(json_response, 'job')
        assert job_in_res[0] == "leader"
        assert status_code == 201

    def test_delete_user_details(self,test_create_user_details ):
        response = requests.delete(BASE_URI + "/api/users/4")
        print(response.status_code)
        status_code = response.status_code
        assert status_code == 204