import TextOR from "@/components/common/TextOR";
import OauthButtons from "@/components/OauthButtons/OauthButtons";
import { css } from "@emotion/react";
import React, { useCallback, useState } from "react";
import MainWrapper from "../MainWrapper";
import InputForm from "./InputForm";
import ValueListType from "./ValueListType";
import WrapStyle from "../WrapStyle";
import { useNavigate } from "react-router-dom";
import SubContentStyle from "../SubContentStyle";

/**
 * Email 회원가입 페이지
 * @returns 
 */
function EmailSignup() {
    const [values, setValues] = useState({
        userId: "",
        name: "",
        userName: "",
        password: ""
    });

    const navigator = useNavigate();

    const onChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setValues({
            ...values,
            [name]: value
        });
    }, [values]);

    const valueList: ValueListType[] = [
        { name: "userId", value: values.userId, placeholder: "휴대폰 번호 또는 이메일 주소", valid: true, type: "text'" },
        { name: "name", value: values.name, placeholder: "성명", valid: true, type: "text" },
        { name: "userName", value: values.userName, placeholder: "사용자 이름", valid: true, type: "text" },
        { name: "password", value: values.password, placeholder: "비밀번호", valid: true, type: "password" }
    ];

    return (
        <React.Fragment>
            <MainWrapper style={style}>
                <h2>친구들의 사진과 동영상을 보려면 가입하세요.</h2>
                <OauthButtons
                    className="oauth-buttons"
                />
                <TextOR className="hr" />
                <InputForm
                    valueList={valueList}
                    onChange={onChange}
                />

            </MainWrapper>
            <div
                css={[WrapStyle, SubContentStyle]}
            >
                <span>계정이 있으신가요? <a onClick={() => navigator("/accounts/login")}>로그인</a></span>
            </div>
        </React.Fragment>

    );
}

const style = css`
    padding: 1.5rem 2.5rem;
    height: auto;
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

    .hr {
        margin: 1rem 0;
    }
`;


export default EmailSignup;