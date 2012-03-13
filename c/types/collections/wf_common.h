#ifndef WF_COMMON_H
#define WF_COMMON_H

union wf_item
{
	signed int signed_int_item;
	unsigned int unsigned_int_item;
	signed char signed_char_item;
	unsigned char unsigned_char_item;
	signed short signed_short_item;
	unsigned short unsigned_short_item;
	signed long signed_long_item;
	unsigned long unsigned_long_item;
	signed long long signed_long_long_item;
	unsigned long long unsigned_long_long_item;
	float float_item;
	double double_item;
	void *pointer_item;
};

enum wf_item_type
{
	WF_SIGNED_CHAR,
	WF_UNSIGNED_CHAR,
	WF_SIGNED_SHORT,
	WF_UNSIGNED_SHORT,
	WF_SIGNED_INT,
	WF_UNSIGNED_INT,
	WF_SIGNED_LONG,
	WF_UNSIGNED_LONG,
	WF_SIGNED_LONG_LONG,
	WF_UNSIGNED_LONG_LONG,
	WF_FLOAT,
	WF_DOUBLE,
	WF_POINTER
};

typedef int (*wf_item_comparison)(enum wf_item_type type, union wf_item left, union wf_item right);

#define TYPE_CASE(enum_name, union_name)\
	case WF_##enum_name##:\
		return left.##union_name##_item == right.##union_name##_item;\

#define SIGN_TYPE_CASE(enum_name, union_name)\
	TYPE_CASE(WF_SIGNED_##enum_name, signed_##union_name)\
	TYPE_CASE(WF_UNSIGNED_##enum_name, unsigned_##union_name)\

int (wf_item_equal)(enum wf_item_type type, union wf_item left, union wf_item right)
{
	switch (type)
	{
		case WF_SIGNED_CHAR:
			return left.signed_char_item == right.signed_char_item;
		case WF_UNSIGNED_CHAR:
			return left.unsigned_char_item == right.unsigned_char_item;
		case WF_SIGNED_SHORT:
			return left.signed_short_item == right.signed_short_item;
		case WF_UNSIGNED_SHORT:
			return left.unsigned_short_item == right.unsigned_short_item;
		case WF_SIGNED_INT:
			return left.signed_int_item == right.signed_int_item;
		case WF_UNSIGNED_INT:
			return left.unsigned_int_item == right.unsigned_int_item;
		case WF_SIGNED_LONG:
			return left.signed_long_item == right.signed_long_item;
		case WF_UNSIGNED_LONG:
			return left.unsigned_long_item == right.unsigned_long_item;
		case WF_SIGNED_LONG_LONG:
			return left.signed_long_long_item == right.signed_long_long_item;
		case WF_UNSIGNED_LONG_LONG:
			return left.unsigned_long_long_item == right.unsigned_long_long_item;
		case WF_FLOAT:
			return left.float_item == right.float_item;
		case WF_DOUBLE:
			return left.double_item == right.double_item;
		case WF_POINTER:
			return left.pointer_item == right.pointer_item;
	}
}

#endif /* WF_COMMON_H */