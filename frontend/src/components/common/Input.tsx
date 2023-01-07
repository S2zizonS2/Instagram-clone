import BaseProps from "@/Type/BaseProps";
import InputProps from "@/Type/InputProps";
import { css } from "@emotion/react";
import React from "react";

interface Props {
    input: InputProps;
}

function Input(props: Props & BaseProps) {
    return (
        <input
            {...props.input}
            css={style}
        />
    );
}

const style = css`
    border-radius: 8px;
`;

export default Input;