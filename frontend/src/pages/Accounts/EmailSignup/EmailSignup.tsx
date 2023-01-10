import OauthButton from "@/components/common/OauthButton";
import OauthButtons from "@/components/OauthButtons/OauthButtons";
import { css } from "@emotion/react";
import React from "react";
import Logo from "../Logo";
import MainWrapper from "../MainWrapper";

/**
 * Email 회원가입 페이지
 * @returns 
 */
function EmailSignup() {
    return (
        <MainWrapper style={style}>
            <Logo />
            <h2>친구들의 사진과 동영상을 보려면 가입하세요.</h2>
            <OauthButtons
                className="oauth-buttons"
            />
        </MainWrapper>
    );
}

const style = css`
    padding: 1.5rem 2.5rem;

    .oauth-buttons {
        width: 100%;
        margin-top: 1.2rem;
    }

    h2 {
        margin-top: 0.5rem;
        font-size: 1.05rem;
        font-weight: 700;
        color: var(--grey-400);
        text-align: center;
        line-height: 1.1;
    }
    
`;

export default EmailSignup;