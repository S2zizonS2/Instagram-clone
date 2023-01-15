import Submit from "@/components/common/Submit";
import { css } from "@emotion/react";
import React from "react";
import inputStyle from "../InputStyle";
import ValidInput from "./ValidInput";
import ValueListType from "./ValueListType";
import SubmitButtonStyle from "../SubmitButtonStyle";

interface InputFormProps {
    valueList: ValueListType[];
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

function InputForm(props: InputFormProps) {
    const { valueList, onChange } = props;
    return (
        <form
            css={[style, inputStyle, SubmitButtonStyle]}
        >
            {
                valueList.map((value, index: number) => (
                    <ValidInput
                        key={index}
                        input={{
                            type: value.type,
                            name: value.name,
                            value: value.value,
                            onChange: onChange
                        }}
                        placeholder={value.placeholder}
                        valid={value.valid}
                    />
                ))
            }
            <b>{"저희 서비스를 이용하는 사람이 회원님의 연락처 정보를 Instagram에 업로드했을 수도 있습니다."}</b>
            <Submit
                value="가입"
            />
        </form>
    );
}

const style = css`
    width: 100%;
    label {
        margin-bottom: 0.5rem;
    }

    b {
        margin-top: 0.5rem;
        font-size: 0.75rem;
        text-align: center;
        color: var(--grey-400);
    }
`;



export default InputForm;