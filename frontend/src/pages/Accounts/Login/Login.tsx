import TextOR from "@/components/common/TextOR";
import { css } from "@emotion/react";
import React from "react";
import MainWrapper from "../MainWrapper";
import InputForm from "./InputForm";
import OauthButtons from "@/components/OauthButtons/OauthButtons";
import { useNavigate } from "react-router-dom";
import SubContentStyle from "../SubContentStyle";
import WrapStyle from "../WrapStyle";

/**
 * /accounts/login 페이지
 * @returns 
 */
function Login() {
    const navigator = useNavigate();
    return (
        <React.Fragment>
            <MainWrapper style={style}>
                <InputForm />
                <TextOR className="hr" />
                <OauthButtons className="oauth-buttons" />
                <a className="forget">비밀번호를 잊으셨나요?</a>
            </MainWrapper>
            <div
                css={[WrapStyle, SubContentStyle]}
            >
                <span>계정이 없으신가요? <a onClick={() => navigator("/accounts/emailSignup")}>가입하기</a></span>
            </div>
        </React.Fragment>
    );
}

const style = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 1.5rem 2.5rem;
    height: auto;
    
    .hr {
        margin-top: 1.5rem;
    }

    .oauth-buttons {
        width: 100%;
        margin-top: 1rem;
    }

    .forget {
        margin-top: 1rem;
        font-size: 0.75rem;
        color: var(--grey-400);
        cursor: pointer;
    }
`;

export default Login;