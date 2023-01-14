import React from "react";
import AnimationPlaceholderInput from "@/components/common/AnimationPlaceholderInput";
import inputStyle from "../InputStyle";
import { css } from "@emotion/react";
import Submit from "@/components/common/Submit";
import SubmitButtonStyle from "../SubmitButtonStyle";

function InputForm() {
    return (
        <form css={[inputStyle, style, SubmitButtonStyle]}>
            <AnimationPlaceholderInput
                input={{
                    type: "text",
                    value: ""
                }}
                placeholder={"전화번호, 사용자 이름 또는 이메일"}
            />
            <AnimationPlaceholderInput
                input={{
                    type: "password",
                    value: ""
                }}
                placeholder={"비밀번호"}
            />
            <Submit
                value={"로그인"}
            />
        </form>
    );
}

const style = css`
    width: 100%;
    margin-top: 2rem;
    label {
        margin-bottom: 0.5rem;
    }
`;

export default InputForm;