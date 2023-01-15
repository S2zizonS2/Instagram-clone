import SignupValues from "@/Type/SignupValues";
import { AxiosResponse } from "axios";
import customAxios from "../customAxios";

function attemptSignupValues(values: SignupValues): Promise<AxiosResponse> {
    const axios = customAxios();
    return axios({
        method: "POST",
        url: "/api/v1/accounts/attempt",
        data: {
            email: values.email,
            password: values.password,
            fullname: values.name,
            username: values.userName,
        }
    });
}

export default attemptSignupValues;