import BaseProps from "@/Type/BaseProps";
import InputProps from "@/Type/InputProps";
import { css } from "@emotion/react";
import React, { useCallback, useState } from "react";

interface Props {
    input: InputProps;
}

function PasswordInput(props: Props & BaseProps) {
    const [shownPassword, setShownPassword] = useState(false);

    const onChangeShown = useCallback(() => {
        setShownPassword((prev) => !prev);
    }, []);

    return (
        <div css={passwordInputStyle}>
            <input
                {...props.input}
                type={shownPassword ? "text" : "password"}
            />
            <span onClick={onChangeShown}>비밀번호 표시</span>
        </div>
    );
}

function Input(props: Props & BaseProps) {
    if (props.input.type === "password") {
        return <PasswordInput {...props} />;
    }
    return (
        <input
            {...props.input}
            css={style}
        />
    );
}

const style = css`
    border-radius: 4px;
    width: 100%;
`;

const passwordInputStyle = css`
    width: 100%;
    position: relative;
    display: inline-flex;
    align-items: center;
    input {
        border-radius: 4px;
        width: 100%;
        padding-right: 6rem;
    }
    span {
        position: absolute;
        right: 0;
        cursor: pointer;
        margin-right: 0.6rem;
    }

`;

export default React.memo(Input);