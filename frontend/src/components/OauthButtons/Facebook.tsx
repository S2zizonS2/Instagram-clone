import React from "react";
import OauthButton from "../common/OauthButton";
import { AiFillFacebook } from "react-icons/ai";
function Facebook() {
    return (
        <OauthButton
            icon={<AiFillFacebook />}
            text="Facebook으로 로그인"
            color={"white"}
            backgroundColor={"#5890FF"}
        />
    );
}

export default Facebook;